# Changelog

## [2.1.0] - 2025-02-02

### Added
- Fixes compatibility with Optifine's Smart Animation (recommend updating LoliASM to 5.23 or greater if you are on this version)
- Fixes zeroed `drawcount` w/ drivers + optimizes when not rendering certain passes

## [2.0.0] - 2025-01-31

### Added
- Fixes compatibility with ChunkAnimator not being fully realized as initial render chunks are not queued for animation
- Fixes compatibility between LoliASM, Optifine and Nothirium, you are now able to use `onDemandAnimatedTextures` properly when all 3 of the mods are installed with animated textures working as intended

## [1.0.3] - 2025-01-28

### Fixed
- Further Cleanroom compatibility, `onDemandAnimatedTextures` config option now correctly reflects fixes

## [1.0.2] - 2024-12-18

### Fixed
- Cleanroom compatibility, Optifine detection now works under Cleanroom

## [1.0.1] - 2024-12-05

### Fixed
- Optifine compatibility, now turns off the tweak when Optifine is present as it is not needed

## [1.0.0] - 2024-12-03

### Added
- First Release! Nothirium (0.3.4 beta) and LoliASM (5.20) compatibility with onDemandAnimatedTextures