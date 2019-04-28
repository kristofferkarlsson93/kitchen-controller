package kitchencontroller.timer

import org.mongodb.scala._


class Mongo {
  // To directly connect to the default server localhost on port 27017
  val mongoClient: MongoClient = MongoClient()

  // Use a Connection String

  // or provide custom MongoClientSettings
  val database: MongoDatabase = mongoClient.getDatabase("mydb")

}
