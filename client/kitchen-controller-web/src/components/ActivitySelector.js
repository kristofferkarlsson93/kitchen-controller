import React, {Component} from 'react';
import TimerWizard from "./TimerWizard.js"

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

export default ActivitySelector;