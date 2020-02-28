pipeline { 
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build'){
            steps{
                echo "Building"
            }
        }
        stage('Test'){
            steps{
                echo "Testing"
            }
        }
        stage('Sanity Check'){
            steps{
                input "Does it look ok?"
            }
        }
        stage('Deploy'){
            steps{
                echo "Deploying"
            }
        }
    }
}
