/***************************************************************************
 * Copyright 2014 Kicker Project (http://kicker-monitoring.net)
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

package kicker.common.record.flow.trace.operation;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

import kicker.common.record.flow.ICallRecord;
import kicker.common.record.flow.IOperationRecord;
import kicker.common.util.registry.IRegistry;

/**
 * @author Andre van Hoorn, Holger Knoche, Jan Waller
 * 
 * @since 1.5
 */
public class CallOperationEvent extends AbstractOperationEvent implements ICallRecord {
	public static final int SIZE = (2 * TYPE_SIZE_LONG) + TYPE_SIZE_INT + (4 * TYPE_SIZE_STRING);
	public static final Class<?>[] TYPES = {
		long.class, // Event.timestamp
		long.class, // TraceEvent.traceId
		int.class, // TraceEvent.orderIndex
		String.class, // OperationEvent.operationSiganture
		String.class, // OperationEvent.classSignature
		String.class, // calleeOperationSignature
		String.class, // calleeClassSiganture
	};

	/** This field should not be exported, because it makes little sense to have no associated class. */
	private static final String NO_CALLEEOPERATIONSIGANTURE = "<no-calleeOperationSiganture>";
	private static final String NO_CALLEECLASSSIGANTURE = ""; // default is empty

	private static final long serialVersionUID = 4139428237569727507L;

	private final String calleeOperationSignature;
	private final String calleeClassSignature;

	/**
	 * This constructor uses the given parameters to initialize the fields of this record.
	 * 
	 * @param timestamp
	 *            The timestamp of this record.
	 * @param traceId
	 *            The trace ID.
	 * @param orderIndex
	 *            The order index.
	 * @param callerOperationSignature
	 *            The caller operation signature. This parameter can be null.
	 * @param callerClassSignature
	 *            The caller class signature. This parameter can be null.
	 * @param calleeOperationSignature
	 *            The callee operation signature. This parameter can be null.
	 * @param calleeClassSignature
	 *            The callee class signature. This parameter can be null.
	 */
	public CallOperationEvent(final long timestamp, final long traceId, final int orderIndex, final String callerOperationSignature,
			final String callerClassSignature, final String calleeOperationSignature, final String calleeClassSignature) {
		super(timestamp, traceId, orderIndex, callerOperationSignature, callerClassSignature);
		this.calleeOperationSignature = (calleeOperationSignature == null) ? NO_CALLEEOPERATIONSIGANTURE : calleeOperationSignature; // NOCS
		this.calleeClassSignature = (calleeClassSignature == null) ? NO_CALLEECLASSSIGANTURE : calleeClassSignature; // NOCS
	}

	/**
	 * This constructor converts the given array into a record. It is recommended to use the array which is the result of a call to {@link #toArray()}.
	 * 
	 * @param values
	 *            The values for the record.
	 */
	public CallOperationEvent(final Object[] values) { // NOPMD (values stored directly)
		super(values, TYPES); // values[0..4]
		this.calleeOperationSignature = (String) values[5];
		this.calleeClassSignature = (String) values[6];
	}

	/**
	 * This constructor uses the given array to initialize the fields of this record.
	 * 
	 * @param values
	 *            The values for the record.
	 * @param types
	 *            The types of the elements in the first array.
	 */
	protected CallOperationEvent(final Object[] values, final Class<?>[] types) { // NOPMD (values stored directly)
		super(values, types); // values[0..4]
		this.calleeOperationSignature = (String) values[5];
		this.calleeClassSignature = (String) values[6];
	}

	/**
	 * This constructor converts the given array into a record.
	 * 
	 * @param buffer
	 *            The bytes for the record.
	 * 
	 * @throws BufferUnderflowException
	 *             if buffer not sufficient
	 */
	public CallOperationEvent(final ByteBuffer buffer, final IRegistry<String> stringRegistry) throws BufferUnderflowException {
		super(buffer, stringRegistry);
		this.calleeOperationSignature = stringRegistry.get(buffer.getInt());
		this.calleeClassSignature = stringRegistry.get(buffer.getInt());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		return new Object[] { this.getTimestamp(), this.getTraceId(), this.getOrderIndex(),
			this.getCallerOperationSignature(), this.getCallerClassSignature(),
			this.getCalleeOperationSignature(), this.getCalleeClassSignature(), };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeBytes(final ByteBuffer buffer, final IRegistry<String> stringRegistry) throws BufferOverflowException {
		buffer.putLong(this.getTimestamp());
		buffer.putLong(this.getTraceId());
		buffer.putInt(this.getOrderIndex());
		buffer.putInt(stringRegistry.get(this.getCallerOperationSignature()));
		buffer.putInt(stringRegistry.get(this.getCallerClassSignature()));
		buffer.putInt(stringRegistry.get(this.getCalleeOperationSignature()));
		buffer.putInt(stringRegistry.get(this.getCalleeClassSignature()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?>[] getValueTypes() {
		return TYPES; // NOPMD
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize() {
		return SIZE;
	}

	@Override
	public final String getCallerOperationSignature() {
		return this.getOperationSignature();
	}

	@Override
	public final String getCallerClassSignature() {
		return this.getClassSignature();
	}

	@Override
	public final String getCalleeOperationSignature() {
		return this.calleeOperationSignature;
	}

	@Override
	public final String getCalleeClassSignature() {
		return this.calleeClassSignature;
	}

	@Override
	public final boolean callsReferencedOperationOf(final IOperationRecord record) {
		return this.getCalleeOperationSignature().equals(record.getOperationSignature()) && this.getCalleeClassSignature().equals(record.getClassSignature());
	}
}