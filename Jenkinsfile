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
                sh 'mv OpaqueRequests.jar /home/ubuntu/'
                sh 'mv requests.conf /home/ubuntu/'
                sh 'echo "java -jar OpaqueRequests.jar" > request.sh'
                sh 'JENKINS_NODE_COOKIE=dontKillMe request.sh'
            }
        }
    }
}
