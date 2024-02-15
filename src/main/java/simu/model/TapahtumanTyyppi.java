package simu.model;

import simu.framework.ITapahtumanTyyppi;

// TODO:
// Tapahtumien tyypit määritellään simulointimallin vaatimusten perusteella
public enum TapahtumanTyyppi implements ITapahtumanTyyppi {
	SAAPUMINEN, INFOTISKI, UUDEN_TILIN_AVAUS, TALLETUS, SIJOITUS_PALVELUT;

}
