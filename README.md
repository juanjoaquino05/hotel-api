# Hotel API

## Overview
The following repo contains a Hotel API develop to Alten (for recruitment purposes).

## Requirements
* Docker Desktop: 4.8.2 (79419) (containing below components)
  * Engine: 20.10.14 
  * Compose: 1.29.2 
  * Credential Helper: v0.6.4 
  * Kubernetes: v1.24.0 
  * Snyk: v1.827.0

## Guidelines
Follow these steps to run the Application.

1. Clone this repository.
2. Build Package.
```bash 
    # Linux, MacOS
    ./mvnw clean package -Dmaven.test.skip
    
    # Windows
    mvnw.cmd clean package -Dmaven.test.skip
```
   ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=juanjoaquino05/alten-hotel-api
3. Build Docker Image.
```bash 
    # Linux, MacOS
    ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=juanjoaquino05/alten-hotel-api
    
    # Windows
    mvnw.cmd spring-boot:build-image -Dspring-boot.build-image.imageName=juanjoaquino05/alten-hotel-api
```

4. Run the following command:
```bash
    # Linux, Mac, Windows
    docker-compose up
```


If there is a problem running the program be sure you have installed and configured Docker correctly. (if not, open an issue in this repo)

## Deploy 
Follow these steps to deploy the application in Kubernetes environment and achieve high availability (deploy downtime is covered by used strategy but depending on cluster nodes you'll have 1 or more replication server).

1. Clone this repository.

2. Modify the file environment/variables.yml.example with you postgres database info and rename it to just variables.yml.
3. Be sure that you run the initial sql script (sql/create_tables.sql) in your database.

4. Run the following commands in your Kubernetes cluster to deploy.
```bash
	# Linux, MacOS
	kubectl apply -f environment/variables.yml
	kubectl apply -f kubernetes-deployment.yml
	kubectl apply -f kubernetes-service.yml
```
5. If you're running on you local docker environment that contains kubernetes, you'll need to run this command to create port forward on your local environment.
```bash
	# Linux, MacOS
	kubectl port-forward svc/springboot-k8s-svc 8085:8085
```

## Disclaimers
* This is an example and intended to demonstrate to app providers a sample of how to approach an implementation. There are potentially other ways to approach it and alternatives could be considered.
* Its possible that the repo is not actively maintained.

## License
MIT

The code in this repository is covered by the included license.

## Support
Please enter an issue in the repo for any questions or problems.
