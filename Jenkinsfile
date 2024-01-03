pipeline {
    agent any 

    triggers {
        pollSCM('* * * * *')
    }

    // Got permission denied while trying to connect to the Docker daemon socket at unix.
    // sudo usermod -a -G docker jenkins
    // restart jenkins server ->  sudo service jenkins restart
    stages {
        
        stage('Maven Compile') {
            steps {
                echo '----------------- This is a compile phase ----------'
                sh 'mvn clean compile'
            }
        }
                
        stage('Maven Build') {
             steps {
                echo '----------------- This is a build phase ----------'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo '----------------- This is a build docker image phase ----------'
                sh '''
                    docker image build -t feedme-webservice .
                '''
            }
        }

        stage('Docker Deploy') {
            steps {
                echo '----------------- This is a docker deploment phase ----------'
                sh '''
                 (if  [ $(docker ps -a | grep feedme-webservice | cut -d " " -f1) ]; then \
                        echo $(docker rm -f feedme-webservice); \
                        echo "---------------- successfully removed feedme-webservice ----------------"
                     else \
                    echo OK; \
                 fi;);
            docker container run  --network feedme-webapp-network --restart always --name feedme-webservice -p 9991:9991 -d feedme-webservice
            '''
            }
        }
    }
}
