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
                sh 'cp OpaqueRequests.jar /home/ubuntu/'
                sh 'cp requests.conf /home/ubuntu/'
                sh "ls"
                sh 'export JENKINS_NODE_COOKIE=dontKillMe'
                sh 'chmod 777 request.sh'
                sh '/bin/sh request.sh'
            }
        }
    }
}
