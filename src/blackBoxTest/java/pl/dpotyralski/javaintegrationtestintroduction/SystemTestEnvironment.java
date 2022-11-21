package pl.dpotyralski.javaintegrationtestintroduction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;

class SystemTestEnvironment {

    private final static Logger LOGGER = LoggerFactory.getLogger(SystemTestEnvironment.class);

    public static DockerComposeContainer environment = new DockerComposeContainer(new File("src/blackBoxTest/resources/bb-docker-compose.yml"))
                    .withLogConsumer("java-integration-test-intro-app", new Slf4jLogConsumer(LOGGER).withPrefix("java-integration-test-intro-app"))
                    .withExposedService("java-integration-test-intro-app", 8080, Wait.forHttp("/actuator/health").forStatusCode(200))
                    .withLocalCompose(true);

    private static boolean environmentStarted = false;

    static void start() {
        if (!environmentStarted) {
            environmentStarted = true;
            environment.start();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> environment.stop()));
        }
    }

    static String serviceUrl() {
        String host = environment.getServiceHost("java-integration-test-intro-app", 8080);
        int port = environment.getServicePort("java-integration-test-intro-app", 8080);
        return "http://%s:%d/".formatted(host, port);
    }
}