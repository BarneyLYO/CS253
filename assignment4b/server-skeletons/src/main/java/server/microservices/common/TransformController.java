package server.microservices.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import server.common.model.TransformedImage;

import java.io.IOException;

import static server.common.Constants.EndPoint.APPLY_TRANSFORM;

/**
 * This Spring controller demonstrates how Spring WebFlux can be used
 * to handle an HTTP POST request via Java reactive programming. This
 * method transforms an image by applying a transform operation,
 * thereby producing a new modified image that is then returned to the
 * client.
 *
 * In Spring's approach to building RESTful web services, HTTP
 * requests are handled by a controller (identified by the
 * {@code @RestController} annotation) that defines the endpoints (aka
 * routes) for each supported operation, i.e., {@code @GetMapping},
 * {@code @PostMapping}, {@code @PutMapping}, and
 * {@code @DeleteMapping}, which correspond to the HTTP GET, POST,
 * PUT, and DELETE calls, respectively.
 *
 * Spring uses the {@code @PostMapping} annotation to map HTTP POST
 * requests onto methods in this controller for requests invoked from
 * any HTTP web client (e.g., a web browser or Android app) or
 * command-line utility (e.g., Curl or Postman).
 *
 * The {@code @ResponseBody} annotation tells a controller that the
 * object returned is automatically serialized into JSON and passed
 * back within the body of HttpResponse object.  The
 * {@code @CrossOrigin} annotation marks the annotated method or type
 * as permitting cross origin requests, which is required for Eureka
 * redirection.
 */
@RestController
@CrossOrigin("*")
@ResponseBody
@EnableDiscoveryClient
public class TransformController {
    /**
     * This auto-wired field connects the {@link TransformController} to
     * the {@link TransformService}.
     */
    @Autowired
    TransformService service;

    /**
     * Request used by Eureka Control panel.
     *
     * @return Print something useful (e.g., the class name)
     */
    @GetMapping("/actuator/info")
    String info() {
        return getClass().getName();
    }

    /**
     * Apply the given {@code transform} to the given {@code
     * image} and return a {@link Mono} that emits the {@link
     * TransformedImage}.
     *
     * @param transform The name of the transformed image
     * @param image     The contents of the image as a {@link MultipartFile}
     * @return A {@link Mono} that emits the {@link TransformedImage}
     */
    // TODO -- you fill in here by adding the appropriate annotations
    // to create a Spring WebFlux endpoint method.
    public Mono<TransformedImage> applyTransform
        (String transform,
         MultipartFile image) {
        // Use the 'service' field to call a TransformService method
        // that applies the transform via the microservice and obtains
        // a TransformedImage object result This TransformedObject
        // object is then wrapped into a Mono returned to the caller.

        // TODO -- replace 'return null' with the proper code.
        return null;
    }

    /**
     * Helper method that gets the image name from a {@link
     * MultipartFile}, which can then be passed to the service {@code
     * applyTransform()} method.
     *
     * @param image MultipartFile containing the image
     * @return The image name suitable for passing to the
     * service applyTransform method
     */
    private String getImageNameFromMultipartFile(MultipartFile image) {
        return image.getOriginalFilename();
    }

    /**
     * Helper method that gets the image bytes from a {@link
     * MultipartFile}, which can then be passed to the service {@code
     * applyTransform()} method.
     *
     * @param image MultipartFile containing the image
     * @return The image bytes suitable for passing to the
     * service applyTransform method
     */
    private byte[] getImageBytesFromMultipartFile(MultipartFile image)
            throws IOException {
        return image.getBytes();
    }
}