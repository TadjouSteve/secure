pipeline {
    agent any

    tools {
        // Indique à Jenkins d'utiliser le JDK installé
        // Assurez-vous d'avoir configuré le JDK dans "Manage Jenkins" -> "Global Tool Configuration"
        jdk 'JDK_17'
        maven 'M3_HOME' 
         
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building the Spring Boot application...'
                // Commande pour compiler l'application et générer le .jar avec Maven ou Gradle
                // Si vous utilisez Maven
                sh 'mvn clean package -DskipTests'
                // Si vous utilisez Gradle
                // sh './gradlew build'
                echo 'la compilation du projet est terminer avec succer'
            }
        }

        stage('Run') {
            steps {
                echo 'Stopping previous instance...'
                // Tente d'arrêter l'instance précédente pour éviter les erreurs de port
                sh 'pkill -f secure'

                echo 'Starting the Spring Boot application...'
                // Lance le fichier .jar en tâche de fond
                sh 'java -jar target/*.jar &' 
            }
        }
    }
}