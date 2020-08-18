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
            }
        }
        stage('Test'){
            steps{
                echo "Testing"
                recordDynatraceSession(
                    envId: 'Sprint',
                    testCase: 'loadtest',
                    tagMatchRules: [
                        [
                            meTypes: [[meType: 'SERVICE']],
                            tags: [
                                [context: 'CONTEXTLESS', key:'OpaqueRequests']
                            ]
                        ]
                    ]) {
                        sh 'java -jar OpaqueRequests.${BUILD_NUMBER}.jar 300'
                }

                perfSigDynatraceReports envId: 'Sprint', nonFunctionalFailure: 1, specFile: "monspec/Opaque_perfsig.json"
            }
        }
        stage('Deploy'){
            steps{
                createDynatraceDeploymentEvent(
                    envId: 'Sprint',
                    tagMatchRules: [
                        [
                            meTypes: [[meType: 'SERVICE']],
                            tags: [
                                [context: 'CONTEXTLESS', key: 'OpaqueRequests']
                            ]
                        ]
                    ]) {
                    echo "Deploying"
                    echo 'Deployed OpaqueRequests.${Build_NUMBER}.jar (Not Really)'
                }
            }
        }
    }
}
