pipeline {
  agent any
  environment { TAG = "${env.BUILD_NUMBER}" }
  stages {
    stage('Test') {
      steps {
        sh 'cd user-service && mvn -q test'
        sh 'cd order-service && mvn -q test'
      }
    }
    stage('Build images') {
      steps {
        sh 'docker build -t user-service:$TAG user-service'
        sh 'docker build -t order-service:$TAG order-service'
      }
    }
    stage('Load into kind') {
      steps {
        sh 'kind load docker-image user-service:$TAG order-service:$TAG --name devops'
      }
    }
    stage('Deploy') {
      steps {
        sh 'kubectl apply -f k8s/'
        sh 'kubectl -n devops-demo set image deployment/user-service user-service=user-service:$TAG'
        sh 'kubectl -n devops-demo set image deployment/order-service order-service=order-service:$TAG'
        sh 'kubectl -n devops-demo rollout status deployment/user-service'
        sh 'kubectl -n devops-demo rollout status deployment/order-service'
      }
    }
  }
}