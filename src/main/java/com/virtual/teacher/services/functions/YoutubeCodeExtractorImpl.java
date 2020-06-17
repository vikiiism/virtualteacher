package com.virtual.teacher.services.functions;

import com.virtual.teacher.services.functions.interfaces.YoutubeCodeExtractor;

public class YoutubeCodeExtractorImpl implements YoutubeCodeExtractor {

    @Override
    public String extractCode(String videoUrl) {
        StringBuilder generateCode = new StringBuilder();
        boolean generate = false;

        for (int i = 0; i < videoUrl.length(); i++) {
            char ch = videoUrl.charAt(i);

            if (generate) {
                generateCode.append(ch);
            }
            if (ch == '=') {
                generate = true;
            }
            if (generateCode.length()==11){
                break;
            }
        }

        return generateCode.toString();
    }

}
