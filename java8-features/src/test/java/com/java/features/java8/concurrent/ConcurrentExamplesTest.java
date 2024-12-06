/**
 * Test suite for Java 8 concurrent package enhancements and features.
 * Demonstrates testing patterns for various concurrent utilities and
 * parallel operations introduced or enhanced in Java 8.
 *
 * Areas tested:
 * - CompletableFuture async operations
 * - Parallel Stream processing
 * - Thread management and scheduling
 * - Concurrent collections
 * - Atomic operations
 *
 * Testing patterns demonstrated:
 * - Async operation verification
 * - Parallel execution testing
 * - Thread safety validation
 * - Exception handling in concurrent code
 * - Timing and performance assertions
 *
 * Example test pattern:
 * ```java
 * @Test
 * void testAsyncOperation() {
 *     CompletableFuture<String> future = CompletableFuture
 *         .supplyAsync(() -> "result")
 *         .thenApply(String::toUpperCase);
 *     
 *     assertEquals("RESULT", future.join());
 * }
 * ```
 *
 * @see java.util.concurrent.CompletableFuture
 * @see java.util.concurrent.atomic
 * @see java.util.stream.Stream
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConcurrentExamplesTest {

    private ConcurrentExamples examples;

    @Before
    public void setUp() {
        examples = new ConcurrentExamples();
    }

    @After
    public void tearDown() {
        examples.shutdown();
    }

    /**
     * Tests basic async computation with CompletableFuture.
     */
    @Test
    public void testAsyncComputation() throws Exception {
        CompletableFuture<String> future = examples.asyncComputation("test");
        String result = future.get(2, TimeUnit.SECONDS);
        assertEquals("Processed: test", result);
    }

    /**
     * Tests chained async operations.
     */
    @Test
    public void testChainedAsyncComputation() throws Exception {
        CompletableFuture<Integer> future = examples.chainedAsyncComputation("test");
        Integer result = future.get(2, TimeUnit.SECONDS);
        assertEquals(Integer.valueOf(15), result); // "Processed: test".length()
    }

    /**
     * Tests combining results from multiple CompletableFutures.
     */
    @Test
    public void testCombineResults() throws Exception {
        CompletableFuture<String> future = examples.combineResults("Hello", "World");
        String result = future.get(2, TimeUnit.SECONDS);
        assertEquals("Processed: Hello - Processed: World", result);
    }

    /**
     * Tests parallel array sorting.
     */
    @Test
    public void testParallelSort() {
        int[] array = {5, 3, 8, 1, 9, 2, 7, 4, 6};
        examples.parallelSort(array);
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertArrayEquals(expected, array);
    }

    /**
     * Tests parallel array prefix operations.
     */
    @Test
    public void testParallelPrefix() {
        double[] array = {1.0, 2.0, 3.0, 4.0};
        examples.parallelPrefix(array);
        double[] expected = {1.0, 3.0, 6.0, 10.0};
        assertArrayEquals(expected, array, 0.0001);
    }

    /**
     * Tests error handling in CompletableFuture.
     */
    @Test
    public void testHandleErrors() throws Exception {
        CompletableFuture<String> futureSuccess = examples.handleErrors("test");
        assertEquals("Processed: test", futureSuccess.get(2, TimeUnit.SECONDS));

        CompletableFuture<String> futureError = examples.handleErrors(null);
        String errorResult = futureError.get(2, TimeUnit.SECONDS);
        assertTrue(errorResult.startsWith("Error occurred:"));
    }

    /**
     * Tests timeout handling with CompletableFuture.
     */
    @Test
    public void testWithTimeout() throws Exception {
        CompletableFuture<String> future = examples.withTimeout("test", 1);
        String result = future.get(2, TimeUnit.SECONDS);
        assertTrue(result.contains("test") || result.equals("Timeout occurred"));
    }

    /**
     * Tests parallel collection processing.
     */
    @Test
    public void testProcessInParallel() throws Exception {
        List<String> inputs = Arrays.asList("A", "B", "C");
        CompletableFuture<List<String>> future = examples.processInParallel(inputs);
        List<String> results = future.get(2, TimeUnit.SECONDS);
        
        assertEquals(3, results.size());
        assertTrue(results.stream().allMatch(s -> s.startsWith("Processed:")));
    }

    /**
     * Tests scheduled task execution.
     */
    @Test
    public void testScheduleTask() throws Exception {
        CompletableFuture<String> future = examples.scheduleTask("test", 1);
        String result = future.get(2, TimeUnit.SECONDS);
        assertEquals("Delayed task completed: test", result);
    }

    /**
     * Tests handling of multiple timeouts.
     */
    @Test
    public void testMultipleTimeouts() throws Exception {
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            futures.add(examples.withTimeout("test" + i, 1));
        }

        List<String> results = futures.stream()
            .map(f -> {
                try {
                    return f.get(2, TimeUnit.SECONDS);
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            })
            .collect(Collectors.toList());

        assertEquals(3, results.size());
        assertTrue(results.stream().allMatch(s -> 
            s.contains("test") || s.equals("Timeout occurred")));
    }
}
