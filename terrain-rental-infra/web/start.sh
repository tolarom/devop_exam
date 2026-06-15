#!/bin/bash
service ssh start
service nginx start

# Run the built jar (wildcard since the exact name depends on pom.xml version)
java -jar /app/target/*.jar