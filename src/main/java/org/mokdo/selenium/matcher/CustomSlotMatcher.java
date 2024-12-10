package org.mokdo.selenium.matcher;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.grid.data.SlotMatcher;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CustomSlotMatcher implements SlotMatcher {


    private static final Logger logger = LoggerFactory.getLogger(CustomSlotMatcher.class);
    @Override
    public boolean matches(Capabilities nodeCapabilities, Capabilities requestedCapabilities) {
        // Extract "se:tags" from capabilities
        String level = System.getProperty("org.slf4j.simpleLogger.defaultLogLevel", "INFO");
        var nodeTags = (List<String>) nodeCapabilities.getCapability("se:tags");
        var requestedTags = (List<String>) requestedCapabilities.getCapability("se:tags");
        logdebug(level,"nodeCapabilities="+nodeCapabilities.toString());
        logdebug(level,"requestedCapabilities="+requestedCapabilities.toString());

//        String message = " nodeTags="+String.join(", ", nodeTags)+" requestedTags="+String.join(", ", requestedTags);
//        logdebug(level,message);


        // Check if both nodeTags and requestedTags are null
        if (nodeTags == null && requestedTags == null) {
            logdebug(level,"Both nodeTags and requestedTags are null; returning true");
            return true;
        }
        // Check if either nodeTags or requestedTags is null
        else if (nodeTags == null || requestedTags == null) {
            logdebug(level,"Either nodeTags or requestedTags is null; returning false");
            return false;
        }

// Proceed if both are not null
        logdebug(level,"nodeTags and requestedTags are not null; proceeding to match logic");
        // Check if any requested tag matches a node tag
        for (String tag : requestedTags) {
            if (nodeTags.contains(tag)) {
                logdebug(level,"Match found. Returning true.");
                return true;
            }
        }

        // No match found
        logdebug(level," No match found return false .");
        return false;
    }
    public void logdebug(String level,String message){
        if (level.equalsIgnoreCase("debug")){
            logger.info(message);
       //     System.out.println(message);
        }

    }
}