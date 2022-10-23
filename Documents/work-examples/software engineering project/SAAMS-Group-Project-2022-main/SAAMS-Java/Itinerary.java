// Generated by Together


/**
 * Describes an aircraft's flight plan.
 * An aircraft entering an airport's airspace has come from the "from" airport, and wishes to land at the "to" airport, before travelling on to the "next" airport.
 * So, an aircraft whose flight descriptor contains an itinerary with "Stirling" as the "to" attribute wishes to land at Stirling now, otherwise it is just passing through local airspace on its way to its destination.
 * Incoming flights supply their Itinerary in their flight descriptor, and the ManagementRecord for the flight extracts the Itinerary and holds it separately.
 * Outbound flights have their Itineraries uploaded to the aircraft as it departs in a newly built FlightDescriptor.
 * @stereotype entity
 * @url element://model:project::SAAMS/design:view:::id3oolzcko4qme4cko4sx40
 * @url element://model:project::SAAMS/design:view:::idwwyucko4qme4cko4sgxi
 * @url element://model:project::SAAMS/design:view:::id2fh3ncko4qme4cko4swe5
 */
public class Itinerary {

  private String from;
  private String to;
  private String next;

  /**
   * Requires names of where the flight is coming from,
   *    * where it is going to now, and where next after that.
   * @param from where the aircraft is coming from
   * @param to   where the aircraft is going
   * @param next  the next destination of the aircraft
   */
  public Itinerary(String from, String to, String next){
    this.from = from;
    this.to = to;
    this.next = next;
  }

  /**
   * Return the from attribute.
   * @tgGet*/
  public String getFrom(){
    return from;
  }

  /**
   *  Return the to attribute.
   * @tgGet*/
  public String getTo(){
     return to;
  }


  /**
   * Return the next attribute.
   * @tgGet*/
  public String getNext(){
     return next;
  }

  public boolean equals(Object o) {
     if (this == o) return true;
     if (!(o instanceof Itinerary)) return false;

     Itinerary i = (Itinerary) o;

     return (this.from.equals(i.from)
             && this.next.equals(i.next)
             && this.to.equals(i.to));
  }

    /**
     * Once a plane has landed at stirling, it will set of to its destination stored in next
     * This method will set the to field to the value of next, and next is invalidated
     * TODO: should next be invalidated?
     */
    public void departingForNextStop() {
        if (to.equals("Stirling")) {
            String valueOfNext = next;
            next = "Error";
            to = valueOfNext;
        }
    }
}
