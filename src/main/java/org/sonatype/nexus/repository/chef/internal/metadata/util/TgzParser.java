package org.sonatype.nexus.repository.chef.internal.metadata.util;

import com.google.common.base.*;
import org.apache.commons.compress.archivers.*;
import org.apache.commons.compress.archivers.tar.*;
import org.apache.commons.compress.compressors.gzip.*;
import org.slf4j.*;
import org.sonatype.goodies.common.*;

import javax.annotation.*;
import javax.inject.*;
import java.io.*;

@Named
@Singleton
public class TgzParser {
    protected static final Logger log = Preconditions.checkNotNull(Loggers.getLogger(TgzParser.class));

    @Nullable
    public InputStream getFileFromInputStream(final InputStream is, String fileName) throws IOException {
        try (GzipCompressorInputStream gzis = new GzipCompressorInputStream(is)) {
            try (TarArchiveInputStream tais = new TarArchiveInputStream(gzis)) {
                ArchiveEntry currentEntry;
                while ((currentEntry = tais.getNextEntry()) != null) {
                    if (currentEntry.getName().toLowerCase().endsWith(fileName)) {
                        log.info(String.format("Found %s file in archive: %s", fileName, currentEntry.getName()));
                        byte[] buf = new byte[(int) currentEntry.getSize()];
                        tais.read(buf, 0, buf.length);
                        return new ByteArrayInputStream(buf);
                    }
                }
            }
        } finally {
            is.close();
        }
        return null;
    }
}