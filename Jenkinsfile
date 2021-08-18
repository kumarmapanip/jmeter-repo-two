pipeline {
    agent any 
    stages {
	stage('Maven Compile'){
		steps{
			echo 'Project compile stage'
			bat label: 'Compilation running', script: '''mvn compile'''
	       	}
	}
	
	stage('Unit Test') {
	   steps {
			echo 'Project Testing stage'
			bat label: 'Test running', script: '''mvn test'''
	       
       		}
   	}

	stage('Jacoco Coverage Report') {
        	steps{
            		jacoco()
		}
	}
        
	stage('Jmeter Tests'){
		steps{
			echo 'Project performance testing stage'
			bat label: 'Project packaging', script: '''cd C:/Users/Kumar Mapanip/Downloads/apache-jmeter-5.4.1/bin
								   jmeter -jjmeter.save.saveservice.output_format=csv -n -t "D:/JMeter_TestPlans/customerDemoFinal.jmx" -l D:/JMeter_TestPlans/report3.csv -q D:/JMeter_TestPlans/user.properties -e -o D:JMeter_TestPlans/html-report-third'''
		}
	} 	
	    
	stage('Maven Package'){
		steps{
			echo 'Project packaging stage'
			bat label: 'Project packaging', script: '''mvn package'''
		}
	} 	
    }
}