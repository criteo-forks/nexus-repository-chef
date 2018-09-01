package org.sonatype.repository.chef.internal.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

/**
 * @since 0.0.1
 */
@Named
@Singleton
public class TgzParser
{
  private static final String METADATA_JSON = "metadata.json";

  @Nullable
  public InputStream getMetadataFromInputStream(final InputStream is) throws IOException {
    try (GzipCompressorInputStream gzis = new GzipCompressorInputStream(is)) {
      try (TarArchiveInputStream tais = new TarArchiveInputStream(gzis)) {
        ArchiveEntry currentEntry;

        while ((currentEntry = tais.getNextEntry()) != null) {
          if (currentEntry.getName().contains(METADATA_JSON)) {
            byte[] buf = new byte[(int) currentEntry.getSize()];

            tais.read(buf, 0, buf.length);

            return new ByteArrayInputStream(buf);
          }
        }
      }
    }
    return null;
  }
}
