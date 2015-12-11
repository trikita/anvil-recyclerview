package trikita.anvil;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public abstract class RenderableRecyclerViewAdapter
        extends RecyclerView.Adapter<RenderableRecyclerViewAdapter.MountHolder> {

    @Override
    public MountHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FrameLayout root = new FrameLayout(parent.getContext());
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return new MountHolder(root, viewType);
    }

    @Override
    public void onBindViewHolder(final MountHolder h, int position) {
        if (h.mount == null) {
            h.mount = new Anvil.Mount((ViewGroup) h.itemView, new Anvil.Renderable() {
                public void view() {
                    RenderableRecyclerViewAdapter.this.view(h);
                }
            });
            h.mount.render();
        } else {
            h.mount.render();
        }
    }

    public static class MountHolder extends RecyclerView.ViewHolder {
        protected final int viewType;
        private Anvil.Mount mount;

        public MountHolder(ViewGroup itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
        }
    }

    public abstract void view(RecyclerView.ViewHolder holder);
}

