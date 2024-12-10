export sel_version=4.14.1
#build
rm -rf ~/.m2/repository
mvn install:install-file -Dfile=selenium-server-$sel_version.jar -DgroupId=org.seleniumhq.selenium -DartifactId=selenium-server -Dversion=$sel_version -Dpackaging=jar
mvn clean package
mvn dependency:copy-dependencies -DoutputDirectory=mydebuenv/dependency
cp target/selenium-setags-matcher-1.0.jar mydebuenv/selenium-setags-matcher-1.0.jar
#deploy Docker
cd mydebuenv
docker build -t docker-10032-repo.repo.devops.poalim.bank/selenium/hub:$sel_version-bnhptags . &&  \
docker push docker-10032-repo.repo.devops.poalim.bank/selenium/hub:$sel_version-bnhptags
cd ..
