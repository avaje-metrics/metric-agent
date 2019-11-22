package org.test.web.api;

import io.avaje.metrics.MockTimedMetric;
import io.avaje.metrics.MetricManager;
import org.junit.Assert;
import org.junit.Test;
import org.test.app.SimpleService;

public class IndexResourceTest extends BaseTest {

  IndexResource orderService = new IndexResource(new SimpleService());

  @Test
  public void testSuccessExecution() {

    orderService.testForceError(false);

    MockTimedMetric metric = MetricManager.testGetTimedMetric("IndexResource.index");
    metric.testReset();

    orderService.index();
    Assert.assertEquals("IndexResource.index", MetricManager.testLastMetricName());
    Assert.assertEquals(1, metric.testGetCount());
    Assert.assertTrue(MetricManager.testLastMetricOpcodeSuccess());

    orderService.index();
    Assert.assertEquals(2, metric.testGetCount());
    Assert.assertTrue(MetricManager.testLastMetricOpcodeSuccess());
  }

  @Test
  public void testErrorExecution() {

    orderService.testForceError(true);

    MockTimedMetric metric = MetricManager.testGetTimedMetric("IndexResource.index");
    metric.testReset();

    try {
      orderService.index();
      Assert.assertTrue("Never get here", false);

    } catch (ResourceException expected) {
      // expecting the error
      Assert.assertEquals(1, metric.testGetCount());
      Assert.assertTrue(MetricManager.testLastMetricOpcodeError());
    }
  }
}
