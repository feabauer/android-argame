package edu.fsu.cs.argame.marker;

import java.util.concurrent.atomic.AtomicReference;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;


public class DestinationOverlay extends Overlay {

  private final AtomicReference<DestinationMarker> destinationReference;
  private final Drawable destinationDrawable;
  private ItemizedOverlay<OverlayItem> internalOverlay;
  private DestinationMarker lastCheckedDestination;
  
  public DestinationOverlay(AtomicReference<DestinationMarker> destinationReference,
      Drawable destinationDrawable) {
    super();
    this.destinationReference = destinationReference;
    this.destinationDrawable = destinationDrawable;
  }
  
  @Override
  public void draw(Canvas canvas, MapView view, boolean shadow) {
    DestinationMarker currentDestination = destinationReference.get();
    if (currentDestination == null) {
      internalOverlay = null;
      return;
    }

    if (lastCheckedDestination != currentDestination) {
      internalOverlay = new DestinationItemizedOverlay(destinationDrawable);
      lastCheckedDestination = currentDestination;
    }
    
    if (internalOverlay != null) {
      internalOverlay.draw(canvas, view, shadow);
    }
  }
  
  private class DestinationItemizedOverlay extends ItemizedOverlay<OverlayItem> {

    public DestinationItemizedOverlay(Drawable defaultMarker) {
      super(boundCenterBottom(defaultMarker));
      populate();
    }
    
    @Override
    protected OverlayItem createItem(int i) {
      return new OverlayItem(destinationReference.get().getLocation().getGeoPoint(), "", "");
    }

    @Override
    public int size() {
      return destinationReference.get() == null ? 0 : 1;
    }
    
  }
}