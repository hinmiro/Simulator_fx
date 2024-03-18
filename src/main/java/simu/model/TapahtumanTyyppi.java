package simu.model;

import simu.framework.ITapahtumanTyyppi;

/**
 * Enum representing the types of events in the simulation model.
 * The types are defined based on the requirements of the simulation model.
 * <p>
 * The types are:
 * <ul>
 *     <li>SAAPUMINEN: Represents the arrival event.</li>
 *     <li>INFOTISKI: Represents the information desk event.</li>
 *     <li>UUDEN_TILIN_AVAUS: Represents the event of opening a new account.</li>
 *     <li>TALLETUS: Represents the deposit event.</li>
 *     <li>SIJOITUS_PALVELUT: Represents the investment services event.</li>
 * </ul>
 * </p>
 * Implements the {@link ITapahtumanTyyppi} interface.
 */
// Tapahtumien tyypit määritellään simulointimallin vaatimusten perusteella
public enum TapahtumanTyyppi implements ITapahtumanTyyppi {
	SAAPUMINEN, INFOTISKI, UUDEN_TILIN_AVAUS, TALLETUS, SIJOITUS_PALVELUT;
}
