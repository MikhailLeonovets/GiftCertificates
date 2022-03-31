node {

    checkout scm

    docker.withRegistry('https://registry.hub.docker.com', 'dockerHub') {

        def customImage = docker.build("leonovets/gift_certificate")

        /* Push the container to the custom Registry */
        customImage.push()
    }
}