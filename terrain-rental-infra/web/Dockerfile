FROM eclipse-temurin:24-jdk

# Install NGINX, SSH server, git, maven, and php-cli (for the php -m requirement)
RUN apt-get update && apt-get install -y \
    nginx \
    openssh-server \
    git \
    maven \
    php-cli \
    && rm -rf /var/lib/apt/lists/*

# Configure SSH (root login with password for grading access)
RUN mkdir /var/run/sshd \
    && echo 'root:Hello@123' | chpasswd \
    && sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config \
    && sed -i 's/#PasswordAuthentication yes/PasswordAuthentication yes/' /etc/ssh/sshd_config

# Clone and build the Spring Boot project (from FQ01)
WORKDIR /app
RUN git clone https://github.com/tolarom/devop_exam.git .
RUN mvn clean package -DskipTests

# NGINX reverse proxy: port 80 -> Spring Boot on 8080
COPY nginx-default.conf /etc/nginx/sites-available/default

COPY start.sh /start.sh
RUN chmod +x /start.sh

EXPOSE 80 8080 22

CMD ["/start.sh"]