export interface KioskModePlugin {
	enterKioskMode(): Promise<void>;
	exitKioskMode(): Promise<void>;
	toggleKioskMode(): Promise<void>;
}
