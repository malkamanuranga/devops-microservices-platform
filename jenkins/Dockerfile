FROM jenkins/jenkins:lts-jdk17
USER root
RUN apt-get update && apt-get install -y docker.io maven curl \
 && curl -L -o /usr/local/bin/kubectl "https://dl.k8s.io/release/v1.30.0/bin/linux/amd64/kubectl" \
 && curl -L -o /usr/local/bin/kind "https://kind.sigs.k8s.io/dl/v0.24.0/kind-linux-amd64" \
 && chmod +x /usr/local/bin/kubectl /usr/local/bin/kind \
 && rm -rf /var/lib/apt/lists/*