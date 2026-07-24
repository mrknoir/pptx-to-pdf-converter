package com.mycompany.pptx_pdf;

import org.jodconverter.core.office.OfficeException;
import org.jodconverter.local.LocalConverter;
import org.jodconverter.local.office.LocalOfficeManager;
import java.io.File;

public class ConverterService {
    
    // This method takes an input PPTX file and an output PDF file
    public static void convert(File inputFile, File outputFile) throws OfficeException {
        
        // 1. Setup the manager (This automatically finds LibreOffice on your system)
        // 1. Setup the manager with a custom 5-minute timeout for heavy PPTX files
        final LocalOfficeManager officeManager = LocalOfficeManager.builder()
                .install()
                .taskExecutionTimeout(300000L) // 300,000 milliseconds = 5 minutes
                .build();
        
        try {
            // 2. Start LibreOffice in the background (headless mode)
            System.out.println("Starting LibreOffice...");
            officeManager.start();
            
            // 3. Perform the conversion
            System.out.println("Converting file...");
            LocalConverter.make()
                .convert(inputFile)
                .to(outputFile)
                .execute();
                
            System.out.println("Conversion complete!");
            
        } finally {
            // 4. Always stop the manager to free up your computer's RAM
            System.out.println("Closing LibreOffice...");
            officeManager.stop();
        }
    }
}