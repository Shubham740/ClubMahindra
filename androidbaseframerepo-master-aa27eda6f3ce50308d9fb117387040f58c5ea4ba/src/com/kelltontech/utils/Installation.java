/*
 *
 *  Proprietary and confidential. Property of Kellton Tech Solutions Ltd. Do not disclose or distribute.
 *  You must have written permission from Kellton Tech Solutions Ltd. to use this code.
 *
 */

package com.kelltontech.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

import android.content.Context;

/**
 * android-developers.blogspot.in/2011/03/identifying-app-installations. html
 */
public class Installation {

    private static String sID = null;
    private static final String INSTALLATION = "INSTALLATION";

    /**
     * @param context
     * @return
     */
    public synchronized static String id(Context context) {
        if (sID != null) {
            return sID;
        }
        File installation = new File(context.getFilesDir(), INSTALLATION);
        try {
            if (!installation.exists()) {
                writeInstallationFile(installation);
            }
            sID = readInstallationFile(installation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sID;
    }

    /**
     * @param installation
     * @return
     * @throws IOException
     */
    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    /**
     * @param installation
     * @throws IOException
     */
    private static void writeInstallationFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
    }
}
