# CS4367Project
Course Project covering phase 1, Implementation of PIT mutation operators

# Introduction
This group project was created for Professor Lingming Zhang by Joseph Felan, Carlos Ortiz, and Barrett Simmons. This project is made to add mutations to the PIT suite code and test them.

# Implementation
The goal for Phase 1 (Mid-term Report) of the project is to understand the ASM bytecode engineering framework, the JavaAgent on-the-fly code instrumentation, and the Maven build system; then to augment the PIT tool to implement the following mutation operators, and evaluate the mutations on 5 real-world project from GitHub.
###### AOD: Replaces and arithmetic expression by each one of the operand, e.g., (a+b) -> (a) and   (a+b) -> (b)
###### ROR: Replaces the rational operators with each of the other ones. It applies every replacements, e.g., (<) -> (>=), (<) -> (!=), and (<) -> (<=)
###### AOR: Replaces an arithmetic expression by each of the other ones, e.g., (a+b) -> (a * b^2)
