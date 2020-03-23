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
                sh 'jar -cvfm OpaqueRequests.${BUILD_NUMBER}.jar Manifest.txt Requests/OpaqueRequests.class'
                sh 'java -jar OpaqueRequests.${BUILD_NUMBER}.jar 20'
            }
        }
        stage('Test'){
            steps{
                echo "Testing"
            }
        }
        stage('Deploy'){
            steps{
                createDynatraceDeploymentEvent(
                    envId: 'Personal Tenant',
                    tagMatchRules: [
                        [
                            meTypes: [[meType: 'SERVICE']],
                            tags: [
                                [context: 'CONTEXTLESS', key: 'JenkinsTest']
                            ]
                        ]
                    ]) {
                echo "Deploying"
                sh 'cp OpaqueRequests.jar /home/ubuntu/'
                sh 'cp requests.conf /home/ubuntu/'
                sh "ls"
                sh 'JENKINS_NODE_COOKIE=dontKillMe /bin/sh request.sh'
                }
            }
        }
    }
}
