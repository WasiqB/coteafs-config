workflow "push-workflow" {
  on = "push"
  resolves = ["test"]
}

action "build" {
  uses = "LucaFeger/action-maven-cli@9d8f23af091bd6f5f0c05c942630939b6e53ce44"
  runs = "mvn clean install"
  args = "-DskipTests=true"
}

action "test" {
  uses = "LucaFeger/action-maven-cli@9d8f23af091bd6f5f0c05c942630939b6e53ce44"
  needs = ["build"]
  runs = "mvn test"
}
