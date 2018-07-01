package kieker.monitoring.writer.elasticapm;

import java.util.LinkedList;
import java.util.List;

import kieker.monitoring.writer.elasticapm.requestPojos.Span;

/**
 * Wrapper class for Span tree model
 *
 * @author Valentin Seifermann
 *
 */
public class SpanNode {

	private Span span;
	private int execIndex;
	private int level;
	private SpanNode parent;
	private List<SpanNode> children;

	public SpanNode() {
		this.children = new LinkedList<>();
	}

	public SpanNode(final SpanNode node) {
		this.children = new LinkedList<>();
		this.span = new Span(node.getSpan());
		this.execIndex = node.getExecIndex();
		this.level = node.getLevel();
		this.parent = node.getParent();
	}

	public void insert(final SpanNode node) {

	}

	public Span getSpan() {
		return this.span;
	}

	public void setSpan(final Span span) {
		this.span = span;
	}

	public int getExecIndex() {
		return this.execIndex;
	}

	public void setExecIndex(final int execIndex) {
		this.execIndex = execIndex;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(final int level) {
		this.level = level;
	}

	public List<SpanNode> getChildren() {
		return this.children;
	}

	public void setChildren(final List<SpanNode> children) {
		this.children = children;
	}

	public SpanNode getParent() {
		return this.parent;
	}

	public void setParent(final SpanNode parent) {
		this.parent = parent;
	}
}
