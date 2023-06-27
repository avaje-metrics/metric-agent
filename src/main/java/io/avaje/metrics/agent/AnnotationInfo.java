package io.avaje.metrics.agent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Collects the annotation information.
 */
public class AnnotationInfo {

  /**
   * The Timed annotation.
   */
  private static final String ANNOTATION_TIMED = "Lio/avaje/metrics/annotation/Timed;";

  /**
   * The NotTimed annotation.
   */
  private static final String ANNOTATION_NOT_TIMED = "Lio/avaje/metrics/annotation/NotTimed;";

  /**
   * Set of JAXRS annotations we look for to detect web endpoints.
   */
  private static final Set<String> JAXRS_ANNOTATIONS = new HashSet<>();
  private static final Set<String> DINJECT_ANNOTATIONS = new HashSet<>();

  static {
    JAXRS_ANNOTATIONS.add("Ljavax/ws/rs/Path;");
    JAXRS_ANNOTATIONS.add("Ljavax/ws/rs/HEAD;");
    JAXRS_ANNOTATIONS.add("Ljavax/ws/rs/GET;");
    JAXRS_ANNOTATIONS.add("Ljavax/ws/rs/PUT;");
    JAXRS_ANNOTATIONS.add("Ljavax/ws/rs/POST;");
    JAXRS_ANNOTATIONS.add("Ljavax/ws/rs/DELETE;");
    JAXRS_ANNOTATIONS.add("Ljavax/ws/rs/OPTIONS;");

    DINJECT_ANNOTATIONS.add("Lio/avaje/http/api/Path;");
    DINJECT_ANNOTATIONS.add("Lio/avaje/http/api/Head;");
    DINJECT_ANNOTATIONS.add("Lio/avaje/http/api/Get;");
    DINJECT_ANNOTATIONS.add("Lio/avaje/http/api/Put;");
    DINJECT_ANNOTATIONS.add("Lio/avaje/http/api/Post;");
    DINJECT_ANNOTATIONS.add("Lio/avaje/http/api/Delete;");
    DINJECT_ANNOTATIONS.add("Lio/avaje/http/api/Options;");
  }

  /**
   * Return true if the annotation is the NotTimed annotation.
   */
  static boolean isNotTimed(String desc) {
    return ANNOTATION_NOT_TIMED.equals(desc);
  }

  /**
   * Return true if the annotation is the Timed annotation.
   */
  static boolean isTimed(String desc) {
    return ANNOTATION_TIMED.equals(desc);
  }

  /**
   * Return true if the annotation is the Timed annotation.
   */
  static boolean isPostConfigured(String desc) {
    return desc.endsWith("PostConfigured;");
  }

  /**
   * Return true if the annotation indicates a JAX-RS endpoint.
   */
  static boolean isJaxrsEndpoint(String desc) {
    return desc.startsWith("Ljavax/ws/rs") && JAXRS_ANNOTATIONS.contains(desc);
  }

  static boolean isAvajeControllerMethod(String desc) {
    return desc.startsWith("Lio/avaje/http/api") && DINJECT_ANNOTATIONS.contains(desc);
  }

  /**
   * The annotations read keyed by their description.
   */
  private final HashMap<String, Object> valueMap = new HashMap<>();

  public AnnotationInfo() {
  }

  /**
   * Add a annotation value.
   */
  public void add(String name, Object value) {

    valueMap.put(name, value);
  }

  public String toString() {
    return valueMap.toString();
  }
}
