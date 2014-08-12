/***************************************************************************
 * Copyright 2014 Kieker Project (http://kieker-monitoring.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.test.common.junit.record.flow.trace.operation.object;

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;

import kieker.common.record.flow.trace.operation.object.CallOperationObjectEvent;
import kieker.common.util.registry.IRegistry;
import kieker.common.util.registry.Registry;

import kieker.test.common.junit.AbstractKiekerTest;
import kieker.test.common.junit.TestValueRangeConstants;
import kieker.test.common.util.record.BookstoreOperationExecutionRecordFactory;
		
/**
 * Creates {@link OperationExecutionRecord}s via the available constructors and
 * checks the values passed values via getters.
 * 
 * @author Generic Kieker
 * 
 * @since 1.10
 */
public class TestGeneratedCallOperationObjectEvent extends AbstractKiekerTest {

	public TestGeneratedCallOperationObjectEvent() {
		// empty default constructor
	}

	/**
	 * Tests {@link CallOperationObjectEvent#TestCallOperationObjectEvent(String, String, long, long, long, String, int, int)}.
	 */
	@Test
	public void testToArray() { // NOPMD (assert missing)
	for (int i=0;i<TestValueRangeConstants.ARRAY_LENGTH;i++) {
			// initialize
			CallOperationObjectEvent record = new CallOperationObjectEvent(TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length], TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length], TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length]);
			
			// check values
			Assert.assertEquals("CallOperationObjectEvent.timestamp values are not equal.", TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length], record.getTimestamp());
			Assert.assertEquals("CallOperationObjectEvent.traceId values are not equal.", TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length], record.getTraceId());
			Assert.assertEquals("CallOperationObjectEvent.orderIndex values are not equal.", TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], record.getOrderIndex());
			Assert.assertEquals("CallOperationObjectEvent.classSignature values are not equal.", TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], record.getClassSignature());
			Assert.assertEquals("CallOperationObjectEvent.operationSignature values are not equal.", TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], record.getOperationSignature());
			Assert.assertEquals("CallOperationObjectEvent.calleeClassSignature values are not equal.", TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], record.getCalleeClassSignature());
			Assert.assertEquals("CallOperationObjectEvent.calleeOperationSignature values are not equal.", TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], record.getCalleeOperationSignature());
			Assert.assertEquals("CallOperationObjectEvent.objectId values are not equal.", TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], record.getObjectId());
			Assert.assertEquals("CallOperationObjectEvent.calleeObjectId values are not equal.", TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], record.getCalleeObjectId());
			
			Object[] values = record.toArray();
			
			Assert.assertNotNull("Record array serialization failed. No values array returned.", values);
			Assert.assertEquals("Record array size does not match expected number of properties 9.", 9, values.length);
			
			// check all object values exist
			Assert.assertNotNull("Array value [0] of type Long must be not null.", values[0]); 
			Assert.assertNotNull("Array value [1] of type Long must be not null.", values[1]); 
			Assert.assertNotNull("Array value [2] of type Integer must be not null.", values[2]); 
			Assert.assertNotNull("Array value [3] of type String must be not null.", values[3]); 
			Assert.assertNotNull("Array value [4] of type String must be not null.", values[4]); 
			Assert.assertNotNull("Array value [5] of type String must be not null.", values[5]); 
			Assert.assertNotNull("Array value [6] of type String must be not null.", values[6]); 
			Assert.assertNotNull("Array value [7] of type Integer must be not null.", values[7]); 
			Assert.assertNotNull("Array value [8] of type Integer must be not null.", values[8]); 
			
			// check all types
			Assert.assertTrue("Type of array value [0] " + values[0].getClass().getCanonicalName() + " does not match the desired type Long", values[0] instanceof Long);
			Assert.assertTrue("Type of array value [1] " + values[1].getClass().getCanonicalName() + " does not match the desired type Long", values[1] instanceof Long);
			Assert.assertTrue("Type of array value [2] " + values[2].getClass().getCanonicalName() + " does not match the desired type Integer", values[2] instanceof Integer);
			Assert.assertTrue("Type of array value [3] " + values[3].getClass().getCanonicalName() + " does not match the desired type String", values[3] instanceof String);
			Assert.assertTrue("Type of array value [4] " + values[4].getClass().getCanonicalName() + " does not match the desired type String", values[4] instanceof String);
			Assert.assertTrue("Type of array value [5] " + values[5].getClass().getCanonicalName() + " does not match the desired type String", values[5] instanceof String);
			Assert.assertTrue("Type of array value [6] " + values[6].getClass().getCanonicalName() + " does not match the desired type String", values[6] instanceof String);
			Assert.assertTrue("Type of array value [7] " + values[7].getClass().getCanonicalName() + " does not match the desired type Integer", values[7] instanceof Integer);
			Assert.assertTrue("Type of array value [8] " + values[8].getClass().getCanonicalName() + " does not match the desired type Integer", values[8] instanceof Integer);
								
			// check all object values 
			Assert.assertEquals("Array value [0] " + values[0] + " does not match the desired value " + TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length],
				TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length], (long) (Long)values[0]
					);
			Assert.assertEquals("Array value [1] " + values[1] + " does not match the desired value " + TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length],
				TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length], (long) (Long)values[1]
					);
			Assert.assertEquals("Array value [2] " + values[2] + " does not match the desired value " + TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length],
				TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], (int) (Integer)values[2]
					);
			Assert.assertEquals("Array value [3] " + values[3] + " does not match the desired value " + TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length],
				TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], values[3]
			);
			Assert.assertEquals("Array value [4] " + values[4] + " does not match the desired value " + TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length],
				TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], values[4]
			);
			Assert.assertEquals("Array value [5] " + values[5] + " does not match the desired value " + TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length],
				TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], values[5]
			);
			Assert.assertEquals("Array value [6] " + values[6] + " does not match the desired value " + TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length],
				TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], values[6]
			);
			Assert.assertEquals("Array value [7] " + values[7] + " does not match the desired value " + TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length],
				TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], (int) (Integer)values[7]
					);
			Assert.assertEquals("Array value [8] " + values[8] + " does not match the desired value " + TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length],
				TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], (int) (Integer)values[8]
					);
		}
	}
	
	/**
	 * Tests {@link CallOperationObjectEvent#TestCallOperationObjectEvent(String, String, long, long, long, String, int, int)}.
	 */
	@Test
	public void testBuffer() { // NOPMD (assert missing)
		for (int i=0;i<TestValueRangeConstants.ARRAY_LENGTH;i++) {
			// initialize
			CallOperationObjectEvent record = new CallOperationObjectEvent(TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length], TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length], TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length]);
			
			// check values
			Assert.assertEquals("CallOperationObjectEvent.timestamp values are not equal.", TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length], record.getTimestamp());
			Assert.assertEquals("CallOperationObjectEvent.traceId values are not equal.", TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length], record.getTraceId());
			Assert.assertEquals("CallOperationObjectEvent.orderIndex values are not equal.", TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], record.getOrderIndex());
			Assert.assertEquals("CallOperationObjectEvent.classSignature values are not equal.", TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], record.getClassSignature());
			Assert.assertEquals("CallOperationObjectEvent.operationSignature values are not equal.", TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], record.getOperationSignature());
			Assert.assertEquals("CallOperationObjectEvent.calleeClassSignature values are not equal.", TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], record.getCalleeClassSignature());
			Assert.assertEquals("CallOperationObjectEvent.calleeOperationSignature values are not equal.", TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], record.getCalleeOperationSignature());
			Assert.assertEquals("CallOperationObjectEvent.objectId values are not equal.", TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], record.getObjectId());
			Assert.assertEquals("CallOperationObjectEvent.calleeObjectId values are not equal.", TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], record.getCalleeObjectId());
		}
	}
	
	/**
	 * Tests {@link CallOperationObjectEvent#TestCallOperationObjectEvent(String, String, long, long, long, String, int, int)}.
	 */
	@Test
	public void testParameterConstruction() { // NOPMD (assert missing)
		for (int i=0;i<TestValueRangeConstants.ARRAY_LENGTH;i++) {
			// initialize
			CallOperationObjectEvent record = new CallOperationObjectEvent(TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length], TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length], TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length]);
			
			// check values
			Assert.assertEquals("CallOperationObjectEvent.timestamp values are not equal.", TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length], record.getTimestamp());
			Assert.assertEquals("CallOperationObjectEvent.traceId values are not equal.", TestValueRangeConstants.LONG_VALUES[i%TestValueRangeConstants.LONG_VALUES.length], record.getTraceId());
			Assert.assertEquals("CallOperationObjectEvent.orderIndex values are not equal.", TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], record.getOrderIndex());
			Assert.assertEquals("CallOperationObjectEvent.classSignature values are not equal.", TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], record.getClassSignature());
			Assert.assertEquals("CallOperationObjectEvent.operationSignature values are not equal.", TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], record.getOperationSignature());
			Assert.assertEquals("CallOperationObjectEvent.calleeClassSignature values are not equal.", TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], record.getCalleeClassSignature());
			Assert.assertEquals("CallOperationObjectEvent.calleeOperationSignature values are not equal.", TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length] == null?"":TestValueRangeConstants.STRING_VALUES[i%TestValueRangeConstants.STRING_VALUES.length], record.getCalleeOperationSignature());
			Assert.assertEquals("CallOperationObjectEvent.objectId values are not equal.", TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], record.getObjectId());
			Assert.assertEquals("CallOperationObjectEvent.calleeObjectId values are not equal.", TestValueRangeConstants.INT_VALUES[i%TestValueRangeConstants.INT_VALUES.length], record.getCalleeObjectId());
		}
	}
}