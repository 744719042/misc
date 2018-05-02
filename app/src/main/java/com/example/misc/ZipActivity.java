package com.example.misc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import utils.FileUtils;
import utils.IOUtils;

public class ZipActivity extends AppCompatActivity implements View.OnClickListener {
    private Button zip;
    private Button unzip;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip);
        zip = (Button) findViewById(R.id.zip);
        zip.setOnClickListener(this);
        unzip = (Button) findViewById(R.id.unzip);
        unzip.setOnClickListener(this);
        info = (TextView) findViewById(R.id.info);
    }

    @Override
    public void onClick(View v) {
        if (v == zip) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File dir = new File(getExternalCacheDir(), "tomcat");
                    if (!dir.exists()) return;

                    ZipOutputStream zipOutputStream = null;
                    try {
                        File zipFile = new File(getExternalCacheDir(), "ziptomcat.zip");
                        if (!zipFile.exists()) {
                            zipFile.createNewFile();
                        }
                        zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
                        zipDir(dir, zipOutputStream, "");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        IOUtils.close(zipOutputStream);
                        zip.post(new Runnable() {
                            @Override
                            public void run() {
                                info.setText("压缩完成");
                            }
                        });
                    }

                }
            }).start();
        } else if (v == unzip) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File dir = new File(getExternalCacheDir(), "tomcat");
                    if (!dir.exists()) {
                        dir.mkdir();
                    }

                    ZipInputStream zipInputStream = null;
                    final StringBuffer stringBuffer = new StringBuffer();
                    try {
                        zipInputStream = new ZipInputStream(getAssets().open("tomcat.zip"));
                        ZipEntry zipEntry = null;
                        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                            if (zipEntry.getName().startsWith(".")) {
                                continue;
                            }

                            if (zipEntry.isDirectory()) {
                                stringBuffer.append("Create Directory: ").append(zipEntry.getName()).append("\n");
                                File childDir = new File(dir, zipEntry.getName());
                                if (!childDir.exists()) {
                                    childDir.mkdirs();
                                }
                            } else {
                                stringBuffer.append("Create File: ").append(zipEntry.getName()).append("\n");
                                File child = new File(dir, zipEntry.getName());
                                FileUtils.copy(zipInputStream, child);
                            }
                        }

                        unzip.post(new Runnable() {
                            @Override
                            public void run() {
                                info.setText(stringBuffer.toString());
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        IOUtils.close(zipInputStream);
                    }
                }
            }).start();
        }
    }

    private void zipDir(File dir, ZipOutputStream zipOutputStream, String path) {
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                zipDir(file, zipOutputStream, path + File.separator + dir.getName());
            }
        } else {
            FileInputStream fis = null;
            try {
                zipOutputStream.putNextEntry(new ZipEntry(path + File.separator + dir.getName()));
                fis = new FileInputStream(dir);
                byte[] bytes = new byte[1024];
                int count = -1;
                while ((count = fis.read(bytes)) != -1) {
                    zipOutputStream.write(bytes, 0, count);
                }
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.close(fis);
            }
        }
    }
}
