# Lab 6 – Design Notes

This document describes the **initial design plan** for the simulation in Lab 6.  
The UML diagram included in this folder represents the **intended architecture** of the program before implementation begins.

The diagram is **not final** and may change during development as the implementation progresses and design decisions evolve.

---

## Package Structure

The program is divided into three main packages:

```
random
  ExponentialRandomStream
  UniformRandomStream

simulation
  Simulator
  Event
  EventQueue
  State
  EventFactory

carwash
  Car
  CarWashState
  ArrivalEvent
  DepartureEvent
  CloseEvent
  StopEvent
  Main
```

The purpose of this structure is to separate the **general simulation framework** from the **specific car wash simulation logic**.

---

# Package Descriptions

## random

This package contains classes used to generate **random numbers** for the simulation.

Note: The classes in this package were **provided by the course instructors** and were not implemented by me. They are included as utilities for generating random numbers used by the simulation.

**ExponentialRandomStream**  
Generates exponentially distributed random values. This is typically used for **inter‑arrival times**, meaning the time between cars arriving at the car wash.

**UniformRandomStream**  
Generates random values within a fixed interval. This can be used for **service times**, such as how long it takes to wash a car.

These classes are independent utilities and can be reused in other simulations.

---

## simulation

This package contains the **generic simulation engine**. The classes here are not specific to the car wash problem and could be reused for other types of simulations.

### Simulator
Controls the overall execution of the simulation.

Responsibilities include:
- Running the simulation loop
- Fetching the next event from the event queue
- Executing events
- Stopping the simulation when a stop condition is reached

### Event
An abstract class representing something that happens at a specific time in the simulation.

All events contain:
- a **time** indicating when they occur
- an **execute()** method that defines what happens when the event is processed

Specific events extend this class.

### EventQueue
Stores upcoming events.

It internally uses a **PriorityQueue<Event>**, which ensures that the event with the **smallest time value** is always processed first. This works because **Event implements Comparable<Event>** and defines **compareTo()** to compare events by their time.

### State
An abstract representation of the current system state.

It typically stores:
- the current simulation time
- shared data used by events

Concrete simulations extend this class to represent their own system state.

### EventFactory
Responsible for **creating and scheduling events**.

Instead of letting events create new events directly, the EventFactory centralizes event creation.  
Either approach can work; this is a design choice to keep responsibilities clearer and reduce coupling between event classes.  
It may use random streams to determine when new events should occur.

This improves separation of concerns and keeps event classes simpler.

---

## carwash

This package contains the **specific implementation of the car wash simulation**.

### Car
Represents a car entering the system.

It may store information such as:
- arrival time
- identifier
- statistics used for analysis

### CarWashState
Extends the abstract **State** class.

It represents the current status of the car wash, such as:
- cars waiting in queue
- available washing stations
- collected statistics

### ArrivalEvent
Represents a car arriving at the car wash.

Typical responsibilities:
- creating a new car
- adding it to the queue or directly to a washing station
- scheduling the next arrival event

### DepartureEvent
Represents a car finishing its wash and leaving the system.

Responsibilities include:
- freeing a washing station
- starting service for the next waiting car (if any)

### CloseEvent
Represents the moment when the car wash stops accepting new cars.

### StopEvent
Represents the end of the simulation.

When this event executes, it triggers the simulator to stop (for example by setting a stop flag or calling something like `Simulator.stop()`).

### Main
Entry point of the program.

Main is responsible for:
- creating the simulation objects
- initializing the simulator
- starting the simulation run

Note: In this design the `Main` class is placed inside the `carwash` package because it starts the specific car wash simulation. However, it could also be placed outside the packages as a general program entry point. In that case, `Main` would simply choose which simulation model to run (for example a car wash, hospital, or other system) while still using the same simulation framework.

---

# Notes on the UML Diagram

The UML diagram in this folder illustrates the **planned relationships between the classes**, including:

- inheritance relationships (for example `ArrivalEvent` extends `Event`)
- associations between components such as `Simulator` and `EventQueue`
- dependencies where one class uses another

The diagram is meant to **clarify the architecture of the system** and support discussion during the design review.

Since the implementation has not yet started, the diagram should be considered a **working design** rather than a finalized specification.

Some class responsibilities, attributes, or method signatures may be adjusted as the program is implemented.