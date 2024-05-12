package org.littleus.sdk.media.pdf;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PDFFetcherTest {

    @org.junit.jupiter.api.Test
    void fetch() throws IOException {
        new PDFFetcher("/AppGallery/Downloads/windows-wsl.pdf","/AppGallery/Downloads/fetch").fetch();
    }
}