pipeline {
    agent any
    stages {
        stage('No-op') {
            steps {
                sh 'ls'
            }
        }
    }
    post {
        success {
            echo 'I succeeeded!'
            mail to: 'jwaffle17@aol.com',
                subject: "Succeeded Pipeline: ${currentBuild.fullDisplayName}",
                body: "Something is right with ${env.Build_URL}"
        }
        unstable {
            echo 'I am unstable :/'
        }
        failure {
            echo 'I failed :('
        }
        changed {
            echo 'Things were different before...'
        }
        always {
            echo 'One way or another, I have finished'
            deleteDir() /* clean up our workspace */
        }
    }
}
