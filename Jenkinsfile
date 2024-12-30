pipeline {
    agent any
    
    tools {
        maven 'Maven 3.x'  
    }
    

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/daksh03/StudentManagement.git', branch: 'main'
            }
        }
        
        stage('Build') {
            steps {
                bat 'mvn clean package' 
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        

        stage('Deploy to Tomcat') {
            steps {
                bat 'copy target\\*.war "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\"'
            }
        }
        
    }
}