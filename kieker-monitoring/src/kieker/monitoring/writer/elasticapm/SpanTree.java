package kieker.monitoring.writer.elasticapm;

public class SpanTree {

	private final SpanNode root;

	private SpanNode lastAdded;

	public SpanTree(final SpanNode root) {
		this.root = root;
		this.lastAdded = root;
	}

	public void insert(final SpanNode node) {
		if (node.getLevel() == this.lastAdded.getLevel()) {

			final SpanNode s = this.lastAdded.getParent();
			s.getChildren().add(node);
			node.setParent(s);
			node.getSpan().setParent(s.getSpan().getId());

			this.lastAdded = node;
		} else if (node.getLevel() == (this.lastAdded.getLevel() + 1)) {
			final SpanNode s = this.lastAdded;
			s.getChildren().add(node);
			node.setParent(s);
			node.getSpan().setParent(s.getSpan().getId());

			this.lastAdded = node;
		} else if (node.getLevel() < this.lastAdded.getLevel()) {
			final int count = this.lastAdded.getLevel() - node.getLevel();
			SpanNode s = this.lastAdded;
			for (int i = 0; i < count; i++) {
				s = s.getParent();
			}
			s.getChildren().add(node);
			node.setParent(s);
			node.getSpan().setParent(s.getSpan().getId());

			this.lastAdded = node;
		}
	}

	public SpanNode getRoot() {
		return this.root;
	}
}
