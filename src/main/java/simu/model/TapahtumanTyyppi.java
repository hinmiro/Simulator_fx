package simu.model;
/**
 * The TapahtumanTyyppi enum defines different types of events used in the simulation model, such as customer arrival and various service processes.
 */

import simu.framework.ITapahtumanTyyppi;

// TODO:
// Tapahtumien tyypit määritellään simulointimallin vaatimusten perusteella
public enum TapahtumanTyyppi implements ITapahtumanTyyppi {
	SAAPUMINEN, INFOTISKI, UUDEN_TILIN_AVAUS, TALLETUS, SIJOITUS_PALVELUT;

}
