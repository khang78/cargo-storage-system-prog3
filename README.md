# Cargo Storage System

## Overview
This project is a Java-based cargo storage system designed to manage customers and cargo items in a constrained storage environment. It supports creating, listing, deleting, and updating cargo entries while enforcing storage capacity limits and handling hazardous cargo information. The application is organized into separate modules for CLI, domain logic, event handling, persistence, GUI, observer, and simulation.

## Features
- CRUD operations for customers and cargo
- Storage capacity management with a configurable maximum capacity
- Unique storage location handling for cargo entries
- Hazard-aware cargo handling with inspection and storage duration tracking
- Event-driven communication between the CLI and the domain logic
- Observer-based notifications for capacity and hazard-related state changes
- Persistence support via JOS and JBP
- JavaFX-based graphical user interface
- Thread-safe simulation for concurrent operations

## Requirements
- JDK 17 or higher
- JavaFX
- JUnit 5
- Mockito

## Domain Logic
The core of the application is built around the following entities:

- Customer: Represents a customer that owns cargo entries
- Cargo: Represents storage units with different cargo types such as dry bulk, liquid bulk, unitised cargo, and combined variants
- Manager: The central service class that coordinates customer and cargo operations, persistence, and observer notifications

## Command-Line Interface (CLI)
The project includes a text-based CLI with several modes:

1. Change mode
   - :c - Add mode
   - :d - Delete mode
   - :u - Update mode
   - :r - List mode
   - :p - Persistence mode
   - :q - Exit
2. Add customers or cargo entries
3. List customers, cargo by type, or hazardous cargo
4. Delete customers or cargo entries
5. Update the storage location of a cargo entry

## Simulation
The simulation package contains a thread-safe simulation for concurrent cargo insertions and deletions. It is intended for testing and demonstration purposes and is built to avoid race-prone behavior.

## Technologies Used
- Java 17
- JavaFX
- JUnit 5
- Mockito

## Concepts Used
- Layered / modular architecture
- Event system
- Observer pattern
- Thread-safety
- Persistence

## How to Run
1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Run one of the provided main classes:
   - runCLI (requires a capacity argument, for example 100)
   - alternativeCLI (same idea as runCLI, with some features disabled)
   - runGUI (starts the JavaFX user interface)
