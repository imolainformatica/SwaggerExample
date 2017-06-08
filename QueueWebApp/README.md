# Coda realizzata tramite tomcat (webapp)

Reallizata a partire da:
https://itexpertsconsultant.wordpress.com/2016/01/25/how-to-implement-jms-with-tomcat-durable-subscription-example/

## Componente "Brocker"
Questo componente ha lo scopo di "attivare" la coda definita all'interno del contesto di tomcat

### Requisiti
Aver collocato nella cartella lib di tomcat:
- activemq-all-5.11.1.jar (da verificare)
- commons-logging-1.1.jar (da verificare)

Aver modificato il file _conf/server.xml_ aggiungendo alle _GlobalNamingResources_


 `<Resource name="ConnectionFactory" auth="Container"
 type="org.apache.activemq.ActiveMQConnectionFactory" description="JMS
 Connection Factory " factory="org.apache.activemq.jndi.JNDIReferenceFactory"
 brokerurl="tcp://localhost:61617"
 brokerName="ActiveMQBroker"
 useEmbeddedBroker="false"/>

 <Resource name="jms/topic/ITExpertsTopic" auth="Container"
 type="org.apache.activemq.command.ActiveMQTopic" factory="org.apache.activemq.jndi.JNDIReferenceFactory"
 physicalName="APP.JMS.TOPIC" />

 <Resource name="jms/queue/ITExpertsQueue" auth="Container"
 type="org.apache.activemq.command.ActiveMQQueue" factory="org.apache.activemq.jndi.JNDIReferenceFactory"
 physicalName=" APP.JMS.QUEUE" />`
 
 Aver aggiunto infondo a _bin/setclasspath.bat/sh_
 
 'set JAVA_OPTS=-Dwebconsole.type=properties -Dwebconsole.jms.url=”tcp://localhost:61617″ -Dwebconsole.jmx.url=”service:jmx:rmi:///jndi/rmi://localhost:1099/ jmxrmi”'
 o
 ''export JAVA_OPTS='-Dwebconsole.type="properties" -Dwebconsole.jms.url="tcp://localhost:61617" -Dwebconsole.jmx.url="service:jmx:rmi:///jndi/rmi://localhost:1099/ jmxrmi"'
 
 ## Utilizzo
 Al deploy del war prodotto da 
 
 'mvn clean package'
 
 verrà avviata la coda "in-memory" usando KahaDB
 
 ## Altri componenti
 
 - Listener
 - Producer
 
 ## punti aperti
 
 - logging
 - posizione file DB coda
 - ordine di avvio (utilizzare file server.xml?)