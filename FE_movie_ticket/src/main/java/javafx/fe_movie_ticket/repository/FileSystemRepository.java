package javafx.fe_movie_ticket.repository;


import javafx.fe_movie_ticket.config.FilesystemConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Repository
@RequiredArgsConstructor
public class FileSystemRepository {

    private static final String DIRECTORY_DOES_NOT_EXIST = "Directory %s does not exist.";
    private final FilesystemConfig filesystemConfig;

    public String persistMovieFile(String filename, byte[] content) throws IOException {

        File directory = new File(filesystemConfig.getMovieDirectory());
        checkExistingMovieDirectory(directory);
        checkPermissions(directory);

        Path filePath = buildMovieFilePath(filename);
        Files.write(filePath, content);
        log.info("File saved: {}", filename);
        return filePath.toString();
    }

    public String persistTicketFile(String filename, byte[] content) throws IOException {

        File directory = new File(filesystemConfig.getTicketDirectory());
        checkExistingTicketDirectory(directory);
        checkPermissions(directory);

        Path filePath = buildTicketFilePath(filename);
        Files.write(filePath, content);
        log.info("File saved: {}", filename);
        return filePath.toString();
    }

//    @SneakyThrows
//    public InputStream getFile(String filePath) {
//
//        Path path = Paths.get(filePath);
//        if (!Files.exists(path)) {
//            throw new IllegalStateException(String.format(DIRECTORY_DOES_NOT_EXIST, filesystemConfig.getDirectory()));
//        }
//
//        try {
//            return Files.newInputStream(path);
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to read file: " + filePath, e);
//        }
//    }

    private Path buildMovieFilePath(String filename) {

        // Validate the filename
        if (filename.contains("..") || filename.contains("\\") || filename.contains("/"))
            throw new IllegalArgumentException("Invalid filename");

        Path filePath = Paths.get(filesystemConfig.getMovieDirectory(), filename);
        if (!filePath.startsWith(filesystemConfig.getMovieDirectory()))
            throw new IllegalArgumentException("Invalid filename");

        return filePath;
    }

    private Path buildTicketFilePath(String filename) {

        // Validate the filename
        if (filename.contains("..") || filename.contains("\\") || filename.contains("/"))
            throw new IllegalArgumentException("Invalid filename");

        Path filePath = Paths.get(filesystemConfig.getTicketDirectory(), filename);
        if (!filePath.startsWith(filesystemConfig.getTicketDirectory()))
            throw new IllegalArgumentException("Invalid filename");

        return filePath;
    }

    private void checkExistingMovieDirectory(File directory) {
        if (!directory.exists())
            throw new IllegalStateException(String.format(DIRECTORY_DOES_NOT_EXIST, filesystemConfig.getMovieDirectory()));
    }

    private void checkExistingTicketDirectory(File directory) {
        if (!directory.exists())
            throw new IllegalStateException(String.format(DIRECTORY_DOES_NOT_EXIST, filesystemConfig.getTicketDirectory()));
    }

    private void checkPermissions(File directory) {
        if (!directory.canRead() || !directory.canWrite())
            throw new IllegalStateException("Directory " + directory.getAbsolutePath() + " is not accessible.");
    }
}
