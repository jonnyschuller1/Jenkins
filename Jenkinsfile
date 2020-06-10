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
                sh "cat monspec/Opaque_perfsig.json"
                recordDynatraceSession(
                    envId: 'Sprint',
                    testCase: 'loadtest',
                    tagMatchRules: [
                        [
                            meTypes: [[meType: 'fakeMEType']],
                            tags: [
                                [context: 'CONTEXTLESS', key:'OpaqueRequests']
                            ]
                        ],
                        [
                            meTypes: [[meType: 'SERVICE']],
                            tags: [
                                [context: 'CONTEXTLESS', key:'OtherTag']
                            ]
                        ],
                        [
                            meTypes: [[meType: 'PROCESS_GROUP']],
                            tags: [
                                [context: 'CONTEXTLESS', key: '', value: 'Stupid']
                            ]
                        ]
                    ]) {
                        sh 'java -jar OpaqueRequests.${BUILD_NUMBER}.jar 30'
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
                sh 'java -jar OpaqueRequests.${BUILD_NUMBER}.jar 1'
                }
            }
        }
    }
}
