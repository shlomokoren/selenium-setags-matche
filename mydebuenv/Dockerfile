from selenium/hub:4.14.1
LABEL org.bnhp.selenium.matcher.image.authors="shlomo.koren@poalim.co.il"
LABEL CustomSlotMatcherVersion=1.0.1b
# Set the working directory inside the container
WORKDIR /opt/selenium

# Copy the required JAR files into the container
COPY selenium-setags-matcher-1.0.jar /opt/selenium/
COPY dependency/ /opt/selenium/dependency/
EXPOSE 4442
EXPOSE 4443
EXPOSE 4444


# Override the default Selenium Hub command to include your custom matcher
CMD java $JVM_SUPPORT_RECOMMENDED_ARGS -cp "selenium-server.jar:/opt/selenium/selenium-setags-matcher-1.0.jar:/opt/selenium/dependency/*" org.openqa.selenium.grid.Main hub --slot-matcher org.bnhp.selenium.matcher.CustomSlotMatcher
