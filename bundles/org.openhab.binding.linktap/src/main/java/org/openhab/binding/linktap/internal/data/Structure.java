package org.openhab.binding.linktap.internal.data;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

//Update with the get methods for attributes
public class Structure implements linktapIdentifiable {
    private String structureId;
    private List<String> thermostats;
    private List<String> smokeCoAlarms;
    private List<String> watertimers;
    private String countryCode;
    private String postalCode;
    private Date peakPeriodStartTime;
    private Date peakPeriodEndTime;
    private String timeZone;
    private Date etaBegin;
    // private SmokeDetector.AlarmState coAlarmState;
    // private SmokeDetector.AlarmState smokeAlarmState;
    private Boolean rhrEnrollment;
    // private Map<String, Where> wheres;
    private HomeAwayState away;
    private String name;
    // private ETA eta;
    private SecurityState wwnSecurityState;

    @Override
    public String getId() {
        return structureId;
    }

    public HomeAwayState getAway() {
        return away;
    }

    public void setAway(HomeAwayState away) {
        this.away = away;
    }

    public String getStructureId() {
        return structureId;
    }

    public List<String> getThermostats() {
        return thermostats;
    }

    public List<String> getSmokeCoAlarms() {
        return smokeCoAlarms;
    }

    public List<String> getCameras() {
        return watertimers;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Date getPeakPeriodStartTime() {
        return peakPeriodStartTime;
    }

    public Date getPeakPeriodEndTime() {
        return peakPeriodEndTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public Date getEtaBegin() {
        return etaBegin;
    }

    /*
     * public AlarmState getCoAlarmState() {
     * return coAlarmState;
     * }
     *
     * public AlarmState getSmokeAlarmState() {
     * return smokeAlarmState;
     * }
     */

    public Boolean isRhrEnrollment() {
        return rhrEnrollment;
    }

    /*
     * public Map<String, Where> getWheres() {
     * return wheres;
     * }
     *
     * public ETA getEta() {
     * return eta;
     * }
     */

    public String getName() {
        return name;
    }

    public SecurityState getWwnSecurityState() {
        return wwnSecurityState;
    }

    public enum HomeAwayState {
        @SerializedName("home")
        HOME,
        @SerializedName("away")
        AWAY,
        @SerializedName("unknown")
        UNKNOWN
    }

    public enum SecurityState {
        @SerializedName("ok")
        OK,
        @SerializedName("deter")
        DETER
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Structure other = (Structure) obj;
        if (away != other.away) {
            return false;
        }
        if (watertimers == null) {
            if (other.watertimers != null) {
                return false;
            }
        } else if (!watertimers.equals(other.watertimers)) {
            return false;
        }
        /*
         * if (coAlarmState != other.coAlarmState) {
         * return false;
         * }
         */
        if (countryCode == null) {
            if (other.countryCode != null) {
                return false;
            }
        } else if (!countryCode.equals(other.countryCode)) {
            return false;
        }
        /*
         * if (eta == null) {
         * if (other.eta != null) {
         * return false;
         * }
         * } else if (!eta.equals(other.eta)) {
         * return false;
         * }
         */
        if (etaBegin == null) {
            if (other.etaBegin != null) {
                return false;
            }
        } else if (!etaBegin.equals(other.etaBegin)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (peakPeriodEndTime == null) {
            if (other.peakPeriodEndTime != null) {
                return false;
            }
        } else if (!peakPeriodEndTime.equals(other.peakPeriodEndTime)) {
            return false;
        }
        if (peakPeriodStartTime == null) {
            if (other.peakPeriodStartTime != null) {
                return false;
            }
        } else if (!peakPeriodStartTime.equals(other.peakPeriodStartTime)) {
            return false;
        }
        if (postalCode == null) {
            if (other.postalCode != null) {
                return false;
            }
        } else if (!postalCode.equals(other.postalCode)) {
            return false;
        }
        if (rhrEnrollment == null) {
            if (other.rhrEnrollment != null) {
                return false;
            }
        } else if (!rhrEnrollment.equals(other.rhrEnrollment)) {
            return false;
        }
        // if (smokeAlarmState != other.smokeAlarmState) {
        // return false;
        // }
        if (smokeCoAlarms == null) {
            if (other.smokeCoAlarms != null) {
                return false;
            }
        } else if (!smokeCoAlarms.equals(other.smokeCoAlarms)) {
            return false;
        }
        if (structureId == null) {
            if (other.structureId != null) {
                return false;
            }
        } else if (!structureId.equals(other.structureId)) {
            return false;
        }
        if (thermostats == null) {
            if (other.thermostats != null) {
                return false;
            }
        } else if (!thermostats.equals(other.thermostats)) {
            return false;
        }
        if (timeZone == null) {
            if (other.timeZone != null) {
                return false;
            }
        } else if (!timeZone.equals(other.timeZone)) {
            return false;
        }
        /*
         * if (wheres == null) {
         * if (other.wheres != null) {
         * return false;
         * }
         * } else if (!wheres.equals(other.wheres)) {
         * return false;
         * }
         */
        if (wwnSecurityState != other.wwnSecurityState) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((away == null) ? 0 : away.hashCode());
        result = prime * result + ((watertimers == null) ? 0 : watertimers.hashCode());
        // result = prime * result + ((coAlarmState == null) ? 0 : coAlarmState.hashCode());
        result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
        // result = prime * result + ((eta == null) ? 0 : eta.hashCode());
        result = prime * result + ((etaBegin == null) ? 0 : etaBegin.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((peakPeriodEndTime == null) ? 0 : peakPeriodEndTime.hashCode());
        result = prime * result + ((peakPeriodStartTime == null) ? 0 : peakPeriodStartTime.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result + ((rhrEnrollment == null) ? 0 : rhrEnrollment.hashCode());
        // result = prime * result + ((smokeAlarmState == null) ? 0 : smokeAlarmState.hashCode());
        result = prime * result + ((smokeCoAlarms == null) ? 0 : smokeCoAlarms.hashCode());
        result = prime * result + ((structureId == null) ? 0 : structureId.hashCode());
        result = prime * result + ((thermostats == null) ? 0 : thermostats.hashCode());
        result = prime * result + ((timeZone == null) ? 0 : timeZone.hashCode());
        // result = prime * result + ((wheres == null) ? 0 : wheres.hashCode());
        result = prime * result + ((wwnSecurityState == null) ? 0 : wwnSecurityState.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        /*
         * builder.append("Structure [structureId=").append(structureId).append(", thermostats=").append(thermostats)
         * .append(", smokeCoAlarms=").append(smokeCoAlarms).append(", cameras=").append(cameras)
         * .append(", countryCode=").append(countryCode).append(", postalCode=").append(postalCode)
         * .append(", peakPeriodStartTime=").append(peakPeriodStartTime).append(", peakPeriodEndTime=")
         * .append(peakPeriodEndTime).append(", timeZone=").append(timeZone).append(", etaBegin=").append(etaBegin)
         * .append(", coAlarmState=").append(coAlarmState).append(", smokeAlarmState=").append(smokeAlarmState)
         * .append(", rhrEnrollment=").append(rhrEnrollment).append(", wheres=").append(wheres).append(", away=")
         * .append(away).append(", name=").append(name).append(", eta=").append(eta).append(", wwnSecurityState=")
         * .append(wwnSecurityState).append("]");
         */

        builder.append("Structure [structureId=").append(structureId).append(", watertimers=").append(watertimers)
                .append("]");

        return builder.toString();
    }

}
