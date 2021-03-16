# vp-api-assignment

### Setup
1. Install docker
2. Create docker image 
   ```
   docker build -t vp-api-docker-image .
3. Run docker image
    ```
    docker run -d -p 8080:8080 vp-api-docker-image
4. Start the application
    ```
    docker-compose up

Note: MongoDB instance already included in docker container, however, I didn't add logic to pre-populate table so feel free to add data via curl command on API or through the vp-web-assignment
