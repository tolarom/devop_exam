pipeline {
    agent any

    triggers {
        pollSCM('*/5 * * * *')
    }

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    environment {
        ANSIBLE_LOCAL_TEMP = "${WORKSPACE}/.ansible-tmp"
        ANSIBLE_REMOTE_TEMP = "/tmp/ansible"
        TEST_SQLITE_DB = "${WORKSPACE}/terrain-rental-test.sqlite"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh '''
                    chmod +x mvnw
                    ./mvnw clean test \
                        -Dspring.datasource.url=jdbc:sqlite:${TEST_SQLITE_DB} \
                        -Dspring.datasource.driver-class-name=org.sqlite.JDBC \
                        -Dspring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect \
                        -Dspring.jpa.hibernate.ddl-auto=create-drop
                '''
            }
        }

        stage('Deploy With Ansible') {
            steps {
                sh '''
                    mkdir -p "${ANSIBLE_LOCAL_TEMP}"
                    ansible-playbook -i ansible/inventory.ini ansible/playbook.yml
                '''
            }
        }
    }

    post {
        failure {
            emailext(
                subject: "Build failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """\
Build failed for ${env.JOB_NAME} #${env.BUILD_NUMBER}.

Project: ${env.JOB_NAME}
Build URL: ${env.BUILD_URL}
Branch: ${env.BRANCH_NAME ?: 'main'}

Please check the Jenkins console output for details.
""",
                recipientProviders: [developers(), culprits()],
                cc: 'srengty@gmail.com,tolarom27@gmail.com'
            )
        }

        success {
            echo 'Build, test, and Ansible deployment completed successfully.'
        }
    }
}