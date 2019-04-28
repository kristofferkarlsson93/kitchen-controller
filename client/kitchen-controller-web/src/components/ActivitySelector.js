import React, {Component} from 'react';

const MenuItems = {
  TIMER: "TIMER"
};

class ActivitySelector extends Component {

  onItemSelected(item) {
    if (item === MenuItems.TIMER)
      this.setState({timerWizardVisible: true})
  }

  state = {
    menuVisible: false,
    timerWizardVisible: false
  };

  render() {
    return (
       <div>
         <div>
           <button onClick={() => this.setState({menuVisible: true})}>
             Välj aktivitet
           </button>
           <div>
             {this.state.menuVisible
                ? <ActivityMenu itemSelected={(item) => this.onItemSelected(item)}/>
                : null}
           </div>
           <div>
             {this.state.timerWizardVisible ? <TimerWizard/> : null}
           </div>
         </div>
       </div>
    );
  }
}

class ActivityMenu extends Component {

  render() {
    return (
       <div>
         <button onClick={() => this.props.itemSelected(MenuItems.TIMER)}>Timer</button>
       </div>
    )
  }
}

class TimerWizard extends Component {

  steps = ["title", "duration"];

  state = {
    step: "title",
    title: "",
    duration: 0
  };

  getView() {
    if (this.state.step === "title") {
      return (
         <div>
           <div><p>Ange namn på timern</p></div>
           <div><input
              type="text"
              value={this.state.title}
              onChange={(event) => this.setState({title: event.target.value})}/></div>
         </div>)
    } else if (this.state.step === "duration") {
      return (
         <div>
           <div><p>Ange timerns längd</p></div>
           <div>
             <input
                type="number"
                value={this.state.duration}
                onChange={(event) => this.setState({duration: event.target.value})}/>
           </div>
         </div>)
    }

  }

  next() {
    const currentStepIndex = this.steps.findIndex(s => s === this.state.step);
    if (currentStepIndex === this.steps.length - 1) {
      this.createTimer()
    }
    this.setState({step: this.steps[currentStepIndex + 1]})
  }

  async createTimer() {
    const data = {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        title: this.state.title,
        startedAt: Date.now(),
        endsAt: new Date(Date.now() + this.state.duration * 60000)
      })
    };
    const respone = await (fetch("http://127.0.0.1:9000/timers", data));
    const json = await (respone.json());
    console.log(json);
  }

  render() {
    const currentView = this.getView();
    return (
       <div>
         {currentView}
         <div>
           <button onClick={() => this.next()}>Nästa</button>
         </div>
       </div>
    )
  }
}

export default ActivitySelector;