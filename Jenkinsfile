pipeline { 
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build'){
            steps{
                echo "Building"
                sh 'javac Requests/OpaqueRequests.java'
                sh 'jar -cvfm OpaqueRequests.jar Manifest.txt Requests/OpaqueRequests.class'
            }
        }
        stage('Test'){
            steps{
                echo "Testing"
            }
        }
        stage('Deploy'){
            steps{
                echo "Deploying"
                sh 'java -DbackgroundProcess=1 -jar OpaqueRequests.jar &'
            }
        }
    }
}
